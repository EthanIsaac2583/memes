import {BaseLayout} from "../../components/base-layout";
import {useEffect} from "react";
import axios from "axios";

export const RootPage = () => {

  useEffect(() => {
    axios({
      method: 'GET',
      url: 'http://localhost:8080/api/v1/quiz-templates'
    }).then((response) => {
      console.log('--------> response', response.data);
    })
  }, []);

  return (
    <BaseLayout>
      <div>Root page</div>
    </BaseLayout>
  );
};
