import styled from "styled-components";

export const BookListWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`
export const UserBookInfoWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  gap: 1.5rem;
  width: 100%;
  
  @media(max-width: 600px){
    flex-direction: column;
  }
 
`;

export const AddBookButton = styled.button`
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top:1rem;
  width: 10%;
  background-color: #de411b;
  color: #fff;
  border: none;
  padding: 0.7rem;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  
  @media(max-width: 600px){
    width: 30%;
  }
`;