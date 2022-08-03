import styled from 'styled-components';

export const BookContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 50%;
  justify-content: center;
  align-self: center;
  margin:auto;
  padding: 1rem 1rem;
  border: 1px solid #dad2d2;
  max-height: 30rem;
  border-radius: 0.5rem;
  
  @media(max-width: 600px){
    width: 100%;
  }
  
`
export const BookCard = styled.div`
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
    max-width: 18rem;
    max-height: 18rem;
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
`