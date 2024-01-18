import {createContext, FC, PropsWithChildren, useState} from 'react';
import {Lead} from "../../model/lead";

type AuthLead = Lead | null;

export const authContext = createContext<AuthLead>(null);

export const AuthProvider: FC<PropsWithChildren> = (props) => {
  const { children } = props;
  const [lead, setLead] = useState<AuthLead>(null);

  return <authContext.Provider value={lead}>{children}</authContext.Provider>;
};
