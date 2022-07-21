import { createGlobalStyle } from 'styled-components';

const GlobalCSS = createGlobalStyle`
  * {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    font-family: 'Lato', sans-serif;
    overflow-x: hidden;

  }

  *:focus {
    outline: none;
  }

  html {
    width: 100%;
  }
`;

export default GlobalCSS;