import styled from "styled-components";

export const ContentWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 1rem 5rem;
  
  @media(max-width: 1280px){
    padding: 1rem 5rem;
  }
  @media(max-width: 1000px){
    flex-direction: column-reverse;
    padding:0.5rem 0.5rem;
  }
  
  @media(max-width: 600px){
    padding:0.5rem 0.5rem;
    flex-direction: column-reverse;
  }
`;

export const Title = styled.h1`
  width: 50%;
  font-size: 3rem;
  color: #183153;
  margin-bottom: 1rem;

  @media(max-width: 1280px){
    width: 100%;
    font-size:2.5rem;
    padding:1rem;
  }

  @media(max-width: 1000px){
    width: 100%;
    font-size:2rem;
    padding:1rem 3rem;
  }
  
  @media(max-width: 600px){
    width: 100%;
    font-size: 1.5rem;
    padding:1rem;
  }
`;

export const AnimationWrapper = styled.div`
  width: 100%;
  max-height: 30rem;
  display: flex;
  justify-content: flex-end;
  
  #book-lover {
    max-height: 100%;
    overflow: hidden;
  }
  
  @media(max-width: 1280px){
    width: 100%;

    #book-lover {
      display: flex;
      justify-content: center;
      align-items: center;
      margin:auto;
      max-height: 100%;
      overflow: hidden;
    }
  }
  @media(max-width: 1000px){
    width: 100%;
    #book-lover {
      display: flex;
      justify-content: center;
      align-items: center;
      margin:auto;
      max-height: 100%;
      overflow: hidden;
    }
  }
  
  @media(max-width: 600px){
    width: 100%;
  }
`;

export const DescriptionWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;

  & > p {
    padding: 2px 2px;
    color: #1e3a61;
    font-size:26px;
  }
  @media(max-width: 1280px){
    & > p {
      font-size: 1.5rem;
      padding: 0 1rem;
  }
    @media(max-width: 1000px) {
      & > p {
        font-size: 1.5rem;
        padding: 0 3rem;
      }
    }
  @media(max-width: 600px) {
    & > p {
      font-size: 1rem;
      padding: 0 1rem;
    }
  }
`
export const ButtonWrapper = styled.button`
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 30%;
  align-items: center;
  padding:1rem 3rem;
  margin-top:2rem;
  border: none;
  background-color: #de411b;
  font-size: 20px;
  color:#f1f1f1;
  cursor:pointer;
  
  @media(max-width: 1280px){
    font-size: 20px;
    margin-left:1rem;
    padding:1rem 2rem;
    width: 10rem;
    margin-bottom: 1rem;
  }
  @media(max-width: 1000px){
    font-size: 20px;
    margin-left:3rem;
    padding:1rem 2rem;
    width: 10rem;
    margin-bottom: 1rem;
  }
  
  @media(max-width: 600px){
    font-size: 15px; 
    margin-left:1rem;
    padding:1rem 2rem;
    width: 8rem;
    margin-bottom: 1rem;
  }
`

