import {FC, PropsWithChildren, useContext} from "react";
import {authContext} from "../auth";
import {BaseLayout} from "../base-layout";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import {Alert} from "react-bootstrap";

export const PropertyGuard: FC<PropsWithChildren> = (props) => {
  const { children } = props;
  const authManager = useContext(authContext);

  if (!authManager.lead) {
    return (
      <BaseLayout>
        <Container>
          <Row>
            <Col>
              <Alert>Private page. Please, sign in to view</Alert>
            </Col>
          </Row>
        </Container>
      </BaseLayout>
    )
  }

  return <>{children}</>;
};
