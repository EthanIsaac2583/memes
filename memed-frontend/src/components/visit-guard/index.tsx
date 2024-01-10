import {FC, PropsWithChildren, useMemo} from "react";
import {ApplicationLocalStorage, StorageKey} from "../../util/application-local-storage";
import {VisitRequester} from "../visit-requester";

export const VisitGuard: FC<PropsWithChildren> = (props) => {
  const { children } = props;

  const visitId = useMemo(() => ApplicationLocalStorage.getItem(StorageKey.VisitId), []);

  if (!visitId) {
    return <VisitRequester />;
  }

  return <>{children}</>
}
