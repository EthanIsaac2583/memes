import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {ProcessTask} from "../../components/process-task";
import {TTask} from "../../model/task";

export const TaskById = () => {
  const { id } = useParams();

  const [task, setTask] = useState<TTask | null>(null);

  useEffect(() => {
    axios<TTask>({
      method: 'GET',
      url: `http://192.168.100.5:8080/api/v1/tasks/${id}`
    })
      .then(response => {
        setTask(response.data);
      });
  }, []);

  if (task === null) {
    return null;
  }

  // // @ts-ignore
  // return (
  //   <div dangerouslySetInnerHTML={{ __html: task.question?.markup }}></div>
  // );

  return <ProcessTask task={task} />
};
