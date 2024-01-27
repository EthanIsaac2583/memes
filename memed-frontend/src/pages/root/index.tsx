import {BaseLayout} from "../../components/base-layout";
import {useEffect, useState} from "react";
import {TQuizTemplate} from "../../model/quiz-template";
import Card from 'react-bootstrap/Card';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import {useRepositories} from "../../repository/repositories-context";
import {useNavigate} from "react-router-dom";
import {Motivation} from "../../components/motivation";

export const RootPage = () => {
  const navigate = useNavigate();
  const repositories = useRepositories();

  const [templates, setTemplates] = useState<Array<TQuizTemplate>>([]);

  const createTemplateProcessingNavigation = (templateId: number) => {
    return () => {
      navigate(`/templates/${templateId}/process-quiz`);
    }
  };

  useEffect(() => {
    repositories?.templateRepository
      .findAll()
      .then(pageable => {
        setTemplates(pageable.content);
      });
  }, []);

  return (
    <BaseLayout>
      <Motivation />
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
                        <Card.Text>{template.description}</Card.Text>
                        <Button variant="dark" onClick={createTemplateProcessingNavigation(template.id)}>Process</Button>
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
