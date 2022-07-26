import styled from "styled-components";
import ReactPaginate from "react-paginate";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";


export const BooksWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`

export const ReactPaginateWrapper = styled(ReactPaginate)`
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: center;
  bottom: 0;

  li {
    list-style-type: none;
    padding: 1rem 1rem;

    a {
      display: flex;
      align-items: center;
      cursor: pointer;
      padding: 1rem 1rem;
      font-size: 14px;
      color: #183153;
    }

    a:hover {
      background-color: #de411b;
      border-radius: 4px;
      color: #ffffff;

      svg {
        color: #ffffff;
      }
    }

    a:focus {
      background-color: #de411b;
      border-radius: 4px;
      color: #ffffff;

      svg {
        color: #ffffff;
      }
    }
  }
`
export const PaginateIconWrapper = styled(FontAwesomeIcon)`
  font-size: 1rem;
  color: #183153;;

  &:hover {
    color: #ffffff;
  }
`;

export const BookInfoWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  gap: 1.5rem;
  width: 100%;

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
`