import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Card from "react-bootstrap/Card";
import {useForm} from "react-hook-form";
import {AuthDto} from "../../../dto/auth-dto";
import {useRepositories} from "../../../repository/repositories-context";

export function AuthLogin() {
  const loginMethods = useForm<AuthDto>();

  const repositories = useRepositories();

  const onSubmit = (data: AuthDto) => {
    repositories?.authRepository
      .login(data)
  };

  return (
    <Container>
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
                </Form.Group>
                <Form.Group className="d-grid">
                  <Form.Text className="text-danger">
                    {loginMethods.formState.errors.root?.message}
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
  );
}
