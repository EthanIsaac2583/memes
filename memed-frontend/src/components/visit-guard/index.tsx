import {FC, PropsWithChildren, useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {ApplicationLocalStorage, StorageKey} from "../../util/application-local-storage";
import {useRepositories} from "../../repository/repositories-context";

export const VisitGuard: FC<PropsWithChildren> = (props) => {
  const { children } = props;
  const navigate = useNavigate();
  const repositories = useRepositories();
  const [verifying, setVerifying] = useState(true);

  const handleNavigateToRoot = () => {
    navigate("/");
  };

  useEffect(() => {
    const handler = async () => {
      if (repositories) {
        const visitId = ApplicationLocalStorage.getItem(StorageKey.VisitId);

        if (visitId) {
          try {
            const visit = await repositories.visitRepository.findById(visitId);
            ApplicationLocalStorage.setItem(StorageKey.VisitId, visit.id);
          } catch (e) {
            ApplicationLocalStorage.removeItem(StorageKey.VisitId);
          }
        }

        if (!visitId) {
          const visit = await repositories.visitRepository.create();
          ApplicationLocalStorage.setItem(StorageKey.VisitId, visit.id);
        }
      }
    };

    handler().then(() => {
      setVerifying(false);
      handleNavigateToRoot();
    });
  }, [repositories]);

  if (verifying) {
    return null;
  }

  return <>{children}</>
}
