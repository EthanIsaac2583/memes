import {FC, PropsWithChildren, useEffect, useState} from "react";
import {ApplicationLocalStorage, StorageKey} from "../../util/application-local-storage";

export const VisitRequester: FC<PropsWithChildren> = (props) => {
  const { children } = props;
  const [fetching, setFetching] = useState(false);

  useEffect(() => {
    const visitId = ApplicationLocalStorage.getItem(StorageKey.VisitId);

    if (!visitId) {
      setFetching(true);
    }
  }, []);

  if (fetching) {
    return null;
  }

  return <>{children}</>;
};
