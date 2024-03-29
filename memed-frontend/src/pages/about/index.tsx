import {BaseLayout} from "../../components/base-layout";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import './styles.scss';

export const AboutPage = () => {
  return (
    <BaseLayout>
      <Container>
        <Row>
          <Col>
            <div>
              <h2>Hi!</h2>
              <p className="about--paragraph">My name is Yerassyl Aitkazy. I am greatly inspired by everything related to spring backend development and see my further growth in it.</p>
              <p className="about--paragraph">Currently, I work as a frontend developer at KIVORK. During this time, I have been actively involved in developing B2B and C2B solutions.</p>
              <p className="about--paragraph mb-0">Project sources</p>
              <div className="about--paragraph">
                <a href="https://github.com/EthanIsaac2583/memes" target="_blank">Github</a>
              </div>
              <p className="about--paragraph mb-0">Contact me</p>
              <div className="about--paragraph">
                <a href="mailto:millennial2583@gmail.com">millennial2583@gmail.com</a>
              </div>
              <div className="about--paragraph">
                <a href="https://www.linkedin.com/in/yerassyl-aitkazy-99205b205/" target="_blank">LinkedIn</a>
              </div>
            </div>
          </Col>
        </Row>
      </Container>
    </BaseLayout>
  );
};
