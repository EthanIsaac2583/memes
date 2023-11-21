import {Link} from "react-router-dom";
import Logo from "../../assets/logo.svg";
import {FC, PropsWithChildren} from "react";

export const BaseLayout: FC<PropsWithChildren> = (props) => {
  return (
    <>
      <header className="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <nav className="container-xxl" aria-label="Main navigation">

          <Link className="navbar-brand" to="/" aria-label="Boosted">
            <img src={Logo} alt="logo" />
          </Link>

          <button className="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#bdNavbar" aria-controls="bdNavbar" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="navbar-collapse collapse" id="bdNavbar">
            <ul className="navbar-nav flex-row flex-wrap bd-navbar-nav pt-2 py-md-0">
              <li className="nav-item col-6 col-md-auto">
                <Link className="nav-link" to="/">Home</Link>
              </li>
              <li className="nav-item col-6 col-md-auto">
                <Link className="nav-link" to="/about">About</Link>
              </li>
            </ul>
          </div>

        </nav>
      </header>
      <main>{props.children}</main>
    </>
  );
};
