import {createContext, FC, PropsWithChildren, useEffect, useMemo, useState} from 'react';
import {Lead} from "../../model/lead";
import {useRepositories} from "../../repository/repositories-context";
import {ApplicationLocalStorage, StorageKey} from "../../util/application-local-storage";

type AuthLead = Lead | null;

type AuthManager = {
  lead: AuthLead;
  setLead: (lead: AuthLead) => void;
};

const DEFAULT_AUTH_MANAGER: AuthManager = {
  lead: null,
  setLead: () => {}
};

export const authContext = createContext<AuthManager>(DEFAULT_AUTH_MANAGER);

export const AuthProvider: FC<PropsWithChildren> = (props) => {
  const { children } = props;
  const [lead, setLead] = useState<AuthLead>(null);
  const repositories = useRepositories();

  const manager = useMemo(() => ({ lead, setLead }), [lead, setLead]);

  useEffect(() => {
    repositories?.leadRepository
      .findMe()
      .then((leadResponse) => {
        setLead(leadResponse);
        ApplicationLocalStorage.setItem(StorageKey.VisitId, leadResponse.visit.id);
      })
      .catch(() => {
        setLead(null);
        ApplicationLocalStorage.removeItem(StorageKey.Token);
      });
  }, []);

  return <authContext.Provider value={manager}>{children}</authContext.Provider>;
};
