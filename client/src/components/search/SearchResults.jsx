import {useParams} from "react-router-dom";
import {useEffect} from "react";
import BookApi from "../reusable/apis/bookApi";
import {useDispatch, useSelector} from "react-redux";
import {addResults} from "../../reducers/searchResults.reducer";

const SearchResults = () =>{
    const params = useParams();
    const searchResults = useSelector(state => state.searchResults)
    const dispatch = useDispatch();

    useEffect(() =>{
       const fetchData = async () => {
           const {query} = params;
           const res = await BookApi.searchBook(query);
           const data = await res.json();
           dispatch(addResults(data))
       }
       fetchData();
    },[])

    console.log(searchResults)

    return(
        <div>
            <h1>
                books
            </h1>
        </div>
    )
}
export default SearchResults;