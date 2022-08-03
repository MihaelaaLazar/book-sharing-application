import styled from "styled-components";

export const BooksWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`
export const BookInfoWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  gap: 1.5rem;
  width: 100%;
  padding:1rem 1rem;
  
  @media(max-width: 600px){
    flex-direction: column;
  }
`
export const BookCardWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  justify-content: flex-start;
  padding: 1rem 1rem;
  border: 1px solid #dad2d2;
  max-height: 30rem;
  border-radius: 0.5rem;

`
export const BookInfo = styled.div`
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
    max-width: 20rem;
    max-height: 20rem;
    cursor: pointer;
    margin-bottom: 1rem;
  }

  table {
    display: flex;
    justify-content: center;
    width: 100%;
  }

  table,
  tbody,
  tr {
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
  
  @media(max-width: 600px) {
    flex-direction: row-reverse;
    justify-content: center;
    align-items: flex-end;

    & > img {
      max-width: 10rem;
      max-height: 10rem;
    }

    table {
      width: 50%;
    }

    table,
    tbody,
    tr {
      width: 100%;
    }
    table tr:last-child {
      display: none;
    }
    
  }
`