import styled, { css, keyframes } from "styled-components";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";


export const Container = styled.div`
  position: relative;
  width: 50px;
  height: 50px;
  border-radius: 50px;
  border: 2px solid #dfdddd;
  padding: 5px;
  background: #ffffff;
  transition: all 0.5s;
  margin: 1rem;

  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  
  @media(max-width: 1280px){
    
  }

  ${({hover}) =>
          hover &&
          css`
            width: 20%;
            border: 2px solid #de411b;
            @media(max-width: 1280px){
              width: 40%;
              border: 2px solid #de411b;
              
            }
          `}
  
  
`;

export const SearchInput = styled.input`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 46px;
  line-height: 30px;
  outline: 0;
  border: 1px solid transparent;
  border-radius:50px;
  font-size: 1rem;
  padding: 0 2rem;
  margin: 0;
  -moz-appearance: none;
  -webkit-appearance: none;
  appearance: none;

  display: ${(props) => (props.showSearchInput ? "block" : "none")};
  
  @media(max-width: 1280px){
    width: 100%;
    height:42px;
    font-size:0.8rem;
  }
`;

const fadeIn = keyframes`
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
`;

const IconCommonCss = css`
  height: 1rem;
  width: 1rem;
  fill: #de411b;;
  z-index: 10;
  animation: ${fadeIn} 1s linear;
  @media(max-width: 1280px){
    width: 0.8rem;
    height: 0.8rem;
  }
`;

export const IconMagnifyingGlass = styled(FontAwesomeIcon)`
  ${IconCommonCss};
  color:#de411b;
`;

export const IconRightArrow = styled(FontAwesomeIcon)`
  ${IconCommonCss};
  align-self: flex-end;
  cursor: pointer;
  color:#de411b;;
  margin-right:0.5rem;

  &:hover {
    fill: #e1dddd;
  }
`;
