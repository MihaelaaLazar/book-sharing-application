import {faChevronLeft, faChevronRight, faEllipsis} from "@fortawesome/free-solid-svg-icons";
import {PaginateIconWrapper, ReactPaginateWrapper} from "./Paginate.style";

const Paginate = ({props, handlePageClick, pageCount}) => {

    return(
        <div>
            {props && props.length ?
                <ReactPaginateWrapper
                    previousLabel={<PaginateIconWrapper icon={faChevronLeft}/>}
                    nextLabel={<PaginateIconWrapper icon={faChevronRight}/>}
                    breakLabel={<PaginateIconWrapper icon={faEllipsis}/>}
                    pageCount={pageCount}
                    marginPagesDisplayed={2}
                    onPageChange={handlePageClick}
                    activeClassName={"active"}
                />
                : null}
        </div>
    )

}
export default Paginate;