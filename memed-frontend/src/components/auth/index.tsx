import {createContext, FC, PropsWithChildren, useMemo, useState} from 'react';
import {Lead} from "../../model/lead";

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

  const manager = useMemo(() => ({ lead, setLead }), [lead, setLead]);

  return <authContext.Provider value={manager}>{children}</authContext.Provider>;
};
