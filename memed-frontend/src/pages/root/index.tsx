import {BaseLayout} from "../../components/base-layout";
import {useEffect, useState} from "react";
import axios from "axios";
import {IPageable} from "../../model/general";
import {TQuizTemplate} from "../../model/quiz-template";

export const RootPage = () => {

  const [templates, setTemplates] = useState<Array<TQuizTemplate>>([]);

  useEffect(() => {
    axios<IPageable<TQuizTemplate>>({
      method: 'GET',
      url: 'http://localhost:8080/api/v1/quiz-templates'
    }).then((response) => {
      setTemplates(response.data.content);
    });
  }, []);

  return (
    <BaseLayout>
      <div>{templates.map(template => {
        return (
            <div style={{ color: '#000' }} key={template.id}>{template.name}</div>
        );
      })}</div>
    </BaseLayout>
  );
};
