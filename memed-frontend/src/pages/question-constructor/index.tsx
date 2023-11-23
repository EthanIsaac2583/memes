import {BaseLayout} from "../../components/base-layout";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import {Form} from "react-bootstrap";

export const QuestionConstructor = () => {
  return (
    <BaseLayout>
      <Form>
        <Container>
          <Row>
            <Col md={4} xs={12}>
                <Form.Select>
                  <option>Open this select menu</option>
                  <option value="1">One</option>
                  <option value="2">Two</option>
                  <option value="3">Three</option>
                </Form.Select>
            </Col>
          </Row>
        </Container>
      </Form>
    </BaseLayout>
  );
};
