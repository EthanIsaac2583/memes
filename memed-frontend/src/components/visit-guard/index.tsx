import {FC, PropsWithChildren, useEffect} from "react";

export const VisitGuard: FC<PropsWithChildren> = (props) => {
  const { children } = props;

  useEffect(() => {

  }, []);

  return <>{children}</>
}
