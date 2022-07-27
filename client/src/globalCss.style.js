import { createGlobalStyle } from 'styled-components';

const GlobalCSS = createGlobalStyle`
  * {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    font-family: 'Lato', sans-serif;

  }

  *:focus {
    outline: none;
  }

  html {
    width: 100%;
  }
  .message-success {
    color: #64b450;
  }

  .message-error {
    color: #e04d4d;
  }

  .message-warning {
    color: #f9a825;
  }
`;

export default GlobalCSS;