import {NavigationApp} from "../navigation-app";
import {useEffect, useState} from "react";
import {Link, useLocation} from 'react-router-dom';
import api from "../../services/api";
import {Pagination} from "../Pagination";
import { FaStethoscope } from "react-icons/fa";

export const ListProfessional = ({ history, match }) => {


    const search = useLocation().search;
    const searchTerm = new URLSearchParams(search).get('searchTerm');

    const paginationSize = 10

    const [tableFilter, setTableFiler] = useState(undefined);
    const [professionalData, setProfessionalData] = useState(undefined);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(undefined);
    const [totalElements, setTotalElements] = useState(undefined);

    const handlePrevNavigation = async () => {
        updateCurrentPage(currentPage-1)
    }

    const updateCurrentPage =  (newPage) => {
        if (newPage >= 0 && newPage<totalPages) {
            setCurrentPage(newPage)
        }
    }

    const handleNextNavigation = async () => {
        updateCurrentPage(currentPage+1)
    }

    const fetchItems = async () => {
        try {
            console.log('searchTerm:' + searchTerm)
            const params = {
                searchTerm: searchTerm,
                page: currentPage,
                size: paginationSize
            };
            console.log(params)
            return await api.get('/professionals', {params})
        } catch (error) {
            console.error("Ocorreu um erro ao buscar os items" + error);
        }
    }

    useEffect(() => {
        async function fetchData() {
            const response = await fetchItems()
            setTotalPages(response.data.totalPages)
            setTotalElements(response.data.totalElements)
            setProfessionalData(response.data.content)
        }
        fetchData();
    }, [currentPage]);

    const filterCustomer = (professional) => {
        if (!tableFilter) return true;
        return ((professional.name).includes(tableFilter))
    }

     const editProfessional = (id) => {
        console.log("clicou" + id);
        return <Link to={`/professional/${id}`} />
     }

    const filterTableElements = () => {
        let key = 0
        if (!professionalData) return;
        return professionalData
            .filter(filterCustomer)
            .map(professional =>
                <tr key={key++} onClick={() => editProfessional(key)} >
                    {/*<td>*/}
                    {/*    <span className="custom-checkbox">*/}
                    {/*        <input type="checkbox" id="checkbox1" name="options[]" value="1"/>*/}
                    {/*        <label htmlFor="checkbox1"></label>*/}
                    {/*    </span>*/}
                    {/*</td>*/}
                    <td>{professional.name}</td>
                </tr>
            )
    }

    return (
        <>
            <NavigationApp/>
            <div className='customForm'>
                <div className="table-wrapper">
                    <div className="table-title">
                        <div className="row">
                            <div className="col-sm-4">
                                <h2 className="list-tile"> <FaStethoscope /> Professionais</h2>
                            </div>

                            <div className="col-sm-6">
                                <form className="form-inline">
                                    <div className="form-group">
                                        <input className="form-control" id="myInput" type="text" placeholder="Pesquisar.."
                                               onChange={e => setTableFiler(e.target.value)}/>
                                        <a href={`/listarcliente?searchTerm=${tableFilter || ''}`} className="btn btn-search"><span className="span-search">Pesquisar</span></a>
                                    </div>
                                </form>
                            </div>

                            <div className="col-sm-2">
                                <a href="/registrarprofessional" className="btn btn-add" data-toggle="modal">
                                   <span className="span-add">Adicionar</span></a>
                            </div>
                        </div>

                    </div>
                    <table className="table table-striped table-hover">
                        <thead>
                        <tr>
                            {/*<th>*/}
							{/*<span className="custom-checkbox">*/}
							{/*	<input type="checkbox" id="selectAll"/>*/}
							{/*	<label htmlFor="selectAll"></label>*/}
							{/*</span>*/}
                            {/*</th>*/}
                            <th>Nome</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            filterTableElements()
                        }
                        </tbody>
                    </table>
                    <Pagination
                        handlePrevNavigation={handlePrevNavigation}
                        handleNextNavigation={handleNextNavigation}
                        totalElements={totalElements}
                        totalPages={totalPages}
                        maxItemsPerPage={paginationSize}
                    />
                </div>
            </div>
        </>
    )
}
