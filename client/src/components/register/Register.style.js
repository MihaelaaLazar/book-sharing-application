import styled from "styled-components";

export const RegisterWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 1rem 5rem;
  align-items: center;
  justify-content: center;
`;
export const FormWrapper = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem 5rem;
  width: 40%;
  gap: 0.7rem;
  box-shadow: inset 0 0 0 2px #eff4f4;


  & > input, textarea {
    display: flex;
    flex-direction: column;
    background-color: #ffffff;
    font-size: 16px;
    line-height: 28px;
    padding: 0 1rem;
    width: 90%;
    min-height: 44px;
    border: unset;
    border-radius: 4px;
    outline-style: solid;
    outline-color: rgba(118, 137, 230, 0.5);
  }

  & > textarea {
    min-height: 100px;
    resize: none;
  }

  & > label {
    display: flex;
    flex-direction: column;
    align-self: flex-start;
    padding: 0 1rem;
  }

  & > button {
    display: flex;
    flex-direction: row;
    border: none;
    font-size: 20px;
    color: #ffffff;
    background-color: #de411b;
    cursor: pointer;
    padding: 0.5rem 2rem;
    border-radius: 5px;
    margin-top: 1rem;

  }

  & > button:disabled,
  & > button[disabled] {
    background-color: #cccccc;
    color: #666666;
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
`