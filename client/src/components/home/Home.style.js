import styled from "styled-components";

export const ContentWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  padding: 1rem 5rem;
`;

export const Title = styled.h1`
  width: 50%;
  font-size: 3rem;
  color: #183153;
  margin-bottom: 1rem;
`;

export const AnimationWrapper = styled.div`
  width: 50%;
  max-height: 30rem;
  display: flex;
  justify-content: flex-end;
  
  #book-lover {
    max-height: 100%;
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
`
export const RegisterButton = styled.button`
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 20%;
  align-items: center;
  padding:1rem 3rem;
  margin-top:2rem;
  border: none;
  background-color: #de411b;
  font-size: 20px;
  color:#f1f1f1;
  cursor:pointer;
  
`
