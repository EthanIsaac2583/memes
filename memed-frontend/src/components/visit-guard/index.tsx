import {FC, PropsWithChildren, useMemo} from "react";
import {ApplicationLocalStorage, StorageKey} from "../../util/application-local-storage";
import {VisitRequester} from "../visit-requester";
import {useNavigate} from "react-router-dom";

export const VisitGuard: FC<PropsWithChildren> = (props) => {
  const { children } = props;
  const navigate = useNavigate();

  const visitId = useMemo(() => ApplicationLocalStorage.getItem(StorageKey.VisitId), []);

  const handleNavigateToRoot = () => {
    navigate("/");
  };

  if (!visitId) {
    return <VisitRequester onRequested={handleNavigateToRoot} />;
  }

  return <>{children}</>
}
