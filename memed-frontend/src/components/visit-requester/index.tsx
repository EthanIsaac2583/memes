import {FC, PropsWithChildren, useEffect, useState} from "react";
import {ApplicationLocalStorage, StorageKey} from "../../util/application-local-storage";
import {useRepositories} from "../../repository/repositories-context";

export const VisitRequester: FC<PropsWithChildren> = (props) => {
  const { children } = props;

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
