import styled from "styled-components";

export const BookListWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`
export const BookListItem = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  gap:0.5rem;
  padding: 1rem;
  width: 100%;
  height: 6rem;
  outline: 1px solid #de411b;

  
  .book-label{
    font-size: 20px;
    font-weight: 600;
  }
  .book-details{
    color: #1e3a61;
    font-size: 20px;
  }
`
export const AddBookButton = styled.button`
  display: flex;
  flex-direction: row;
  justify-content: center;
  width: 10%;
  background-color: #de411b;
  color: #fff;
  border: none;
  padding: 1rem;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
`;