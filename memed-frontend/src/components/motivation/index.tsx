import {Modal} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import {useContext} from "react";
import {authContext} from "../auth";

export const Motivation = () => {
  const authManager = useContext(authContext);

  const handleCloseMotivation = () => {
    authManager.setShowMotivation(false);
  };

  return (
    <Modal
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
      show={authManager.showMotivation}
      onHide={handleCloseMotivation}
    >
      <Modal.Header closeButton onClick={handleCloseMotivation}>
        <Modal.Title id="contained-modal-title-vcenter">
          Authentication
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <h5>Login or register</h5>
        <p>
           And you will be able to track all your quiz history
        </p>
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={handleCloseMotivation}>Close</Button>
      </Modal.Footer>
    </Modal>
  );
}
