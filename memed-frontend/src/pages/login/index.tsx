import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Card from "react-bootstrap/Card";
import {useForm} from "react-hook-form";
import {AuthDto} from "../../dto/auth-dto";
import {useRepositories} from "../../repository/repositories-context";
import {useContext, useState} from "react";
import {authContext} from "../../components/auth";
import {ApplicationLocalStorage, StorageKey} from "../../util/application-local-storage";
import {ErrorResponse} from "../../model/error-response";
import {useNavigate} from "react-router-dom";
import {BaseLayout} from "../../components/base-layout";

export function AuthLoginPage() {
  const navigate = useNavigate()
  const repositories = useRepositories();
  const authManager = useContext(authContext);
  const loginMethods = useForm<AuthDto>();
  const [errorResponse, setErrorResponse] = useState<ErrorResponse | null>(null);

  const onSubmit = (data: AuthDto) => {
    repositories?.authRepository
      .login(data)
      .then((authResponse) => {
        authManager.setLead(authResponse.lead);
        ApplicationLocalStorage.setItem(StorageKey.Token, authResponse.token);
        ApplicationLocalStorage.setItem(StorageKey.VisitId, authResponse.lead.visit.id);
        navigate("/");
      })
      .catch(setErrorResponse);
  };

  return (
    <BaseLayout>
      <Container>
        <Row className="mt-3">
          <Col>
            <h3>Login</h3>
          </Col>
        </Row>
        <Row className="mt-5">
          <Col md={4} />
          <Col md={4}>
            <Card>
              <Card.Body>
                <Form onSubmit={loginMethods.handleSubmit(onSubmit)}>
                  <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control
                      {...loginMethods.register('username', { required: 'Username is required' })}
                      type="text"
                      placeholder="Enter username"
                    />
                    <Form.Text className="text-danger">
                      {loginMethods.formState.errors.username?.message}
                    </Form.Text>
                    <Form.Text className="text-danger">
                      {errorResponse?.fieldErrors.username}
                    </Form.Text>
                  </Form.Group>

                  <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                      {...loginMethods.register('password', { required: 'Password is required' })}
                      type="password"
                      placeholder="Enter password"
                    />
                    <Form.Text className="text-danger">
                      {loginMethods.formState.errors.password?.message}
                    </Form.Text>
                    <Form.Text className="text-danger">
                      {errorResponse?.fieldErrors.password}
                    </Form.Text>
                  </Form.Group>
                  <Form.Group className="d-grid">
                    <Form.Text className="text-danger">
                      {loginMethods.formState.errors.root?.message}
                    </Form.Text>
                    <Form.Text className="text-danger">
                      {errorResponse?.message}
                    </Form.Text>
                    <Button variant="dark" type="submit">
                      Submit
                    </Button>
                  </Form.Group>
                </Form>
              </Card.Body>
            </Card>
          </Col>
          <Col md={4} />
        </Row>
      </Container>
    </BaseLayout>
  );
}
