import Logo from '../../assets/logo.svg';

export const RootPage = () => {
  return (
    <header className="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <nav className="container-xxl" aria-label="Main navigation">

        <a className="navbar-brand" href="/" aria-label="Boosted">
          <img src={Logo} alt="logo" />
        </a>

        <button className="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#bdNavbar" aria-controls="bdNavbar" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="navbar-collapse collapse" id="bdNavbar">
          <ul className="navbar-nav flex-row flex-wrap bd-navbar-nav pt-2 py-md-0">
            <li className="nav-item col-6 col-md-auto">
              <a className="nav-link" href="/">Home</a>
            </li>
            <li className="nav-item col-6 col-md-auto">
              <a className="nav-link" href="/about">About</a>
            </li>
          </ul>
        </div>

      </nav>
    </header>
  )
};
