import styled from "styled-components";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

export const ModalWrapper = styled.div`
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.4);
  display: flex;
  justify-content: center;
  align-items: center;
`
export const CloseButton = styled(FontAwesomeIcon)`
  object-fit: contain;
  position: absolute;
  top: 4rem;
  right: 4rem;
  font-size: 3rem;
  cursor: pointer;
  color: #d3d3d3;
`
export const ModalCard = styled.div`
  width: 50%;
  background-color: #fff;
  display: flex;
  max-width: 50%
`

export const ModalThumbnail = styled.section`
  flex: 1 1;
  width: 50%;
  overflow: hidden;

  img {
    object-fit: cover;
    height: 100%;
    width: 100%;
    max-width: 20rem;
  }
`;

export const ModalContent = styled.section`
  flex: 1 1;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  gap:1rem;
`;
export const BookTitleWrapper = styled.div`
  display: flex;
  flex-direction: column;
`

export const BookTitle = styled.div`
  font-size: 28px;
  color: #1e3a61;
  font-weight: 600;
`;

export const BookAuthor = styled.div`
  font-size: 24px;
  color: #de411b;
`;

export const BookDescription = styled.section`
  color: #919191;
  padding: 1rem 0;
`;

export const BookDetails = styled.table`
  width: 100%;
  display: flex;
  gap: 1rem;

  tbody {
    tr {
      td {
        color: #1e3a61;
        font-weight: 500;

        &:first-child {
          font-weight: 600;
        }
        
        &:last-child{
          padding-left:1rem;
        }
      }
    }
  }
`;

export const RentTheBookButton = styled.button`
  background-color: #de411b;
  color: #fff;
  border: none;
  padding: 1rem 2rem;
  font-size: 15px;
  cursor: pointer;
  margin-top: 0.3rem;
  width: 60%;
`;

export const DropdownWrapper = styled.select`
  width: 40%;
  border: 1px solid #d8d8d8;
  border-radius: 0.25em;
  padding: 0.25rem 0.5rem;
  font-size: 0.9rem;
  cursor: pointer;
  line-height: 1.1;
  background-color: #fff;
  background-image: linear-gradient(to top, #f9f9f9, #fff 55%);

`

export const ModalButtonWrapper = styled.button`
  background-color: #de411b;
  color: #fff;
  border: none;
  padding: 1rem;
  font-size: 14px;
  cursor: pointer;
  margin-top: 1rem;
  width: 60%;
`

