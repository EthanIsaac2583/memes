import {useParams} from "react-router-dom";
import {useEffect} from "react";
import axios from "axios";

export const TaskById = () => {
  const { id } = useParams();

  useEffect(() => {
    axios({
      method: 'GET',
      url: `http://192.168.100.5:8080/api/v1/tasks/${id}`
    })
      .then(response => {
        console.log('-------> response', response);
      })
  }, []);

  return (
    <div>task by id</div>
  );
};
