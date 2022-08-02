import styled from "styled-components";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'

export const HeaderWrapper = styled.header`
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  background-color: #fff;
  padding: 1rem 5rem;
  width: 100%;
  
  @media(max-width: 1280px){
    padding:0.5rem 0.5rem;
  }
`;

export const HorizontalLine = styled.div`
  width: 95%;
  height: 2px;
  background-color: #f1f1f1;
  margin: 0 auto;
`;

export const LoginIcon = styled(FontAwesomeIcon)`
  font-size: 2rem;
  color: #de411b;
  padding: 1rem 1rem;
  
  @media(max-width: 1280px){
    font-size:1.2rem;
    padding:0.5rem 0.5rem;
  }
`;
export const UserIconWrapper = styled.div`
  position: relative;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;

  @media(max-width: 1280px){
    font-size:1.2rem;
    padding:0.5rem 0.5rem;
  }
  
`
export const IconWrapper = styled(FontAwesomeIcon)`
  font-size: 2rem;
  color: #de411b;
  padding: 1rem 0;
  cursor: pointer;
`;

export const Logo = styled.img`
  object-fit: contain;
  position: absolute;
  left: 5rem;
  top: 1rem;
  max-width: 5rem;
  cursor: pointer;

  @media(max-width: 1280px){
    left:1rem;
  }
`;

export const ButtonWrapper = styled.button`
  display: flex;
  flex-direction: row;
  align-items: center;
  color: #de411b;
  background-color: transparent;
  border: none;
  cursor: pointer;
  font-size: 1.5rem;
  justify-content: center;

  @media(max-width: 1280px){
    font-size:1.2rem;
    padding:0.5rem 0.5rem;
  }
`


