import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {ProcessTask} from "../../components/process-task";
import {TTask} from "../../model/task";
import {BaseLayout} from "../../components/base-layout";

export const TaskById = () => {
  const { id } = useParams();

  const [task, setTask] = useState<TTask | null>(null);

  useEffect(() => {
    axios<TTask>({
      method: 'GET',
      url: `http://localhost:8080/api/v1/tasks/${id}`
    })
      .then(response => {
        setTask(response.data);
      });
  }, []);

  if (task === null) {
    return null;
  }

  return (
    <BaseLayout>
      <ProcessTask task={task} />
    </BaseLayout>
  );
};
