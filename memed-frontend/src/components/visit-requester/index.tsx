import {FC, ReactNode, useEffect, useState} from "react";
import {ApplicationLocalStorage, StorageKey} from "../../util/application-local-storage";
import {useRepositories} from "../../repository/repositories-context";

interface IProps {
  children?: ReactNode;
  onRequested?: () => void;
}

export const VisitRequester: FC<IProps> = (props) => {
  const { children, onRequested } = props;

  const [fetching, setFetching] = useState(false);

  const repositories = useRepositories();

  useEffect(() => {
    const visitId = ApplicationLocalStorage.getItem(StorageKey.VisitId);

    if (!visitId) {
      setFetching(true);
      repositories?.visitRepository
        .create()
        .then(visit => {
          ApplicationLocalStorage.setItem(StorageKey.VisitId, visit.id);
          setFetching(false);
          onRequested?.();
        })
        .catch(() => {
          setFetching(false);
        });
    }
  }, []);

  if (fetching) {
    return null;
  }

  return <>{children}</>;
};
