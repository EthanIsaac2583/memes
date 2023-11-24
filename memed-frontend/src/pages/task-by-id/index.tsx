import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {TTaskDto} from "../../dto/task";

export const TaskById = () => {
  const { id } = useParams();

  const [task, setTask] = useState<TTaskDto | null>(null);

  useEffect(() => {
    axios<TTaskDto>({
      method: 'GET',
      url: `http://192.168.100.5:8080/api/v1/tasks/${id}`
    })
      .then(response => {
        setTask(response.data);
      });
  }, []);

  if (!task) {
    return null;
  }

  // @ts-ignore
  return (
    <div dangerouslySetInnerHTML={{ __html: task.question?.markup }}></div>
  );
};
