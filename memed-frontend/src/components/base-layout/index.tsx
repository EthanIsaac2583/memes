import {FC, PropsWithChildren} from "react";
import Logo from "../../assets/logo.svg";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

export const BaseLayout: FC<PropsWithChildren> = (props) => {
  return (
    <>
      <Navbar sticky="top" expand="sm" bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="/">
            <img src={Logo} alt="logo" />
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav>
              <Nav.Link href="/about">About</Nav.Link>
            </Nav>
            <Nav>
              <Nav.Link href="/task-constructor">Task Constructor</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <main className="my-3">{props.children}</main>
    </>
  );
};
