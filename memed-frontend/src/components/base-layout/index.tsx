import {FC, PropsWithChildren} from "react";
import Logo from "../../assets/logo.svg";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import {Link} from "react-router-dom";

export const BaseLayout: FC<PropsWithChildren> = (props) => {
  return (
    <>
      <Navbar sticky="top" expand="sm" bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand as={Link} to="/">
            <img src={Logo} alt="logo" />
          </Navbar.Brand>
          <Nav className="me-auto flex-row">
            <Nav.Link as={Link} to="/" className="mx-2">Quizzes</Nav.Link>
            <Nav.Link as={Link} to="/about" className="mx-2">About</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <main className="my-3">{props.children}</main>
    </>
  );
};
