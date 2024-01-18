import {FC, PropsWithChildren, useContext} from "react";
import Logo from "../../assets/logo.svg";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {Link, useNavigate} from "react-router-dom";
import {authContext} from "../auth";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import {ApplicationLocalStorage, StorageKey} from "../../util/application-local-storage";

export const BaseLayout: FC<PropsWithChildren> = (props) => {
  const authManager = useContext(authContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    authManager.setLead(null);
    ApplicationLocalStorage.removeItem(StorageKey.Token);
    navigate("/");
  };

  return (
    <>
      <Navbar sticky="top" expand="sm" bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand as={Link} to="/">
            <img src={Logo} alt="logo" />
          </Navbar.Brand>
          <Nav className="me-auto flex-row">
            <Nav.Link as={Link} to="/about" className="mx-2">About</Nav.Link>
            <Nav.Link as={Link} to="/" className="mx-2">Quizzes</Nav.Link>
            {authManager.lead && (
              <Nav.Link as={Link} to="/history" className="mx-2">History</Nav.Link>
            )}
          </Nav>
          {authManager.lead ? (
            <Nav className="flex-row">
              <Button onClick={handleLogout} variant="outline-secondary">Logout</Button>
            </Nav>
            ) : (
            <Nav className="flex-row">
              <Nav.Link as={Link} to="/auth/login" className="mx-2">Login</Nav.Link>
              <Nav.Link as={Link} to="/auth/register" className="mx-2">Register</Nav.Link>
            </Nav>
          )}
        </Container>
      </Navbar>
      {authManager.lead && (
        <Container>
          <Row className="mt-3">
            <Col>
              <h3>{`☀️☀️☀️ Good day, ${authManager.lead.username}!`}</h3>
            </Col>
          </Row>
        </Container>
      )}
      <main className="my-3">{props.children}</main>
    </>
  );
};
