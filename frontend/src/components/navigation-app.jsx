export const NavigationApp = (props) => {
  return (
    <nav id='menu' className='navbar navbar-default navbar-fixed-top'>
      <div className='container'>
        <div className='navbar-header'>
          <button
            type='button'
            className='navbar-toggle collapsed'
            data-toggle='collapse'
            data-target='#bs-example-navbar-collapse-1'
          >
            {' '}
            <span className='sr-only'>Toggle navigation</span>{' '}
            <span className='icon-bar'></span>{' '}
            <span className='icon-bar'></span>{' '}
            <span className='icon-bar'></span>{' '}
          </button>
          <a className='navbar-brand page-scroll' href='#page-top'>
            Quiropraxista
          </a>{' '}
        </div>

        <div
          className='collapse navbar-collapse'
          id='bs-example-navbar-collapse-1'
        >
          <ul className='nav navbar-nav navbar-right'>
            <li>
              <a href='/login' className='page-scroll'>
                Sair
              </a>
            </li>
            <li>
              <a href='/agenda' className='page-scroll'>
                Agenda
              </a>
            </li>
            <li>
              <a href='/listarcliente' className='page-scroll'>
                Clientes
              </a>
            </li>
            <li>
              <a href='/listarprofessional' className='page-scroll'>
                Professionais
              </a>
            </li>
            <li>
              <a href='/listarservico' className='page-scroll'>
                Tipos de Serviço
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  )
}
