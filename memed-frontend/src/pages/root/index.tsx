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
    // todo move to repository
    axios<IPageable<TQuizTemplate>>({
      method: 'GET',
      url: 'http://ec2-3-79-152-118.eu-central-1.compute.amazonaws.com/api/v1/templates'
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
                        <Button variant="dark" href={`/templates/${template.id}/process-quiz`}>Process</Button>
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
