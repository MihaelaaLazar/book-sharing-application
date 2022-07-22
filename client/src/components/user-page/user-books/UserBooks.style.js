import styled from "styled-components";

export const BookListWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`
export const UserBookInfoWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  gap: 1.5rem;
  width: 100%;
`;
export const CardWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  justify-content: flex-start;
  padding: 2rem 1rem;
  border: 1px solid #dad2d2;
`;
export const CardInfoWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  font-size: 15px;
  color: #183153;

  & > img {
    display: flex;
    justify-content: center;
    align-self: center;
    max-width: 25rem;
    max-height: 25rem;
    margin-bottom: 1rem;

  }

  table {
    display: flex;
    justify-content: center;
    width: 100%;
  }
  table,
  tbody,
  tr{
    width: 100%;
  }

  table tr td:first-child {
    color: #183153;
    font-weight: 600;
    width: 50%;
  }

  table tr td:last-child {
    color: #183153;
    text-align: left;
    width: 50%;
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
  padding: 1rem;
  border-radius: 5px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
`;