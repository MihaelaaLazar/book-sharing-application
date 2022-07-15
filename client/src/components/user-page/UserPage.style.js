import styled from "styled-components";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

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
  gap:1px
`
export const LabelIcon = styled(FontAwesomeIcon)`
  font-size: 2rem;
  color: #de411b;
  padding: 1rem 1rem;
`