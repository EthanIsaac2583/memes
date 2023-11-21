import {BaseLayout} from "../../components/base-layout";
import {useEffect, useState} from "react";
import axios from "axios";
import {IPageable} from "../../model/general";
import {TQuizTemplate} from "../../model/quiz-template";
import Card from 'react-bootstrap/Card';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';

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
      <Container>
        <Row>
          {templates.map(template => {
            return (
                <Col key={template.id} md={3} xs={12} sm={12}>
                    <Card
                        bg="secondary"
                        text='light'
                        className="mb-2"
                    >
                      <Card.Header>Quiz</Card.Header>
                      <Card.Body>
                        <Card.Title>{template.name}</Card.Title>
                        <Button variant="dark" href={`/quiz-templates/${template.id}/run`}>Run</Button>
                      </Card.Body>
                    </Card>
                </Col>
            );
          })}
        </Row>
      </Container>
    </BaseLayout>
  );
};
