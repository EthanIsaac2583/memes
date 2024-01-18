import {BaseLayout} from "../../components/base-layout";
import {useState} from "react";
import {Page} from "../../model/page";
import {Quiz} from "../../model/quiz";

export const HistoryPage = () => {
  const [quizPage, setQuizPage] = useState<Page<Quiz> | null>(null);



  return (
    <BaseLayout>
      <div>history</div>
    </BaseLayout>
  );
};
