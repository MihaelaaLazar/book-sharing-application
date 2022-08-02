import styled from "styled-components";

export const PopupWrapper = styled.div`
  position: absolute;
  top:6rem;
  right: -2.4rem;
  display: flex;
  flex-direction: column;;
  align-items: flex-end;
  border: 2px solid #e3e1e1;
  border-radius: 5px;
  background-color: #ffffff;
  z-index: 10;
  width: 15rem;
  @media(max-width: 1280px){
    right:1rem;
  }
`;

export const PopupHeader = styled.div`
  display: flex;
  flex-direction: column;
`;

export const PopupHeaderText = styled.div`
  display: flex;
  font-size: 1.1rem;
  color:#282c34;
  font-weight: 600;
  padding:0.5rem 0.5rem;
`;

export const PopupInformation = styled.div`
  display: flex;
  flex-direction: column;
  padding:0.5rem 0.5rem;
`;
export const PopupContent = styled.div`
  display: flex;
  flex-direction: column;
  padding:0.5rem 0.5rem;

`;
export const LogoutButton = styled.button`
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  max-width: 40%;
  align-self: flex-end;
  padding:0.5rem;
  border:none;
  background-color: #de411b;
  color: #fff;
  border-radius: 3px;
  font-size: 0.9rem;
  font-weight: 400;
  cursor: pointer;
  
`;


