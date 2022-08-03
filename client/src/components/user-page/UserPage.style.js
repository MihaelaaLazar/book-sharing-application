import styled from "styled-components";

export const UserPageWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 100%;
  
`
export const UserContent = styled.div`
  display: flex;
  width: 95%;
  
`
export const LabelIconWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 1px;
  font-weight: 400;
  font-size: 16px;
  

  @media(max-width: 600px){
    font-size:10px;
    flex-wrap: wrap;

  }

`
export const ImageIcon = styled.img`
  max-width: 4rem;
  padding: 1rem 1rem;
  
  @media(max-width: 600px){
    max-width: 3.5rem;
  }
  
`