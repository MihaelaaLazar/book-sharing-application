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
  position: absolute;
  bottom: 0;

  li {
    list-style-type: none;
    padding: 1rem 1rem;

    a {
      display: flex;
      align-items: center;
      cursor: pointer;
      padding: 1rem 1rem;
      font-size: 16px;
      color: #183153;
    }

    a:hover {
      background-color: #de411b;
      border-radius: 4px;
      color: #ffffff;
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
  justify-content: flex-start;
  gap: 1.5rem;
  width: 100%;

`
export const BookCardWrapper = styled.div`
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: flex-start;
  padding: 2rem 1rem;
  border: 1px solid #dad2d2;

`
export const BookInfo = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  font-size: 18px;
  color: #183153;

  & > img {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 50%;
    object-fit: contain;
  }
  div{
    display: flex;
    justify-content: flex-start;
    width: 100%;
    letter-spacing: 1px;
    white-space: pre;
  }

  div span {
    color: #183153;
    font-weight: 600;
  }
`