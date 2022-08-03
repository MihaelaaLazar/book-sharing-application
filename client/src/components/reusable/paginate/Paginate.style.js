import styled from 'styled-components'
import ReactPaginate from "react-paginate";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";


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
    &.active a{
      background-color: #de411b;
      border-radius: 4px;
      color: #ffffff;
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
  
  @media(max-width: 600px){
    li {
      a {
        font-size: 12px;
        padding: 0.5rem 0.5rem;
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