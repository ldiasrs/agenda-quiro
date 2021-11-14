export const Pagination = ({handlePrevNavigation, handleNextNavigation, totalElements, totalPages, maxItemsPerPage}) => {

    const getTotalItems = () => {
        return maxItemsPerPage
    }

    const getPagesClick = () => {
        const pages = []
        for (let i = 0; i < totalPages; i++) {
            pages.push(<li className="page-item"><button href="#" className="page-link">{i+1}</button></li>)
        }
        return pages
    }

    return (
        <div className="clearfix">
            <div className="hint-text">Showing <b>{getTotalItems()}</b> out of <b>{totalElements}</b> entries</div>
            <ul className="pagination">
                <li className="page-item disabled"><button onClick={handlePrevNavigation}>Previous</button></li>
                {
                    getPagesClick()
                }
                <li className="page-item"><button onClick={handleNextNavigation} className="page-link">Next</button></li>
            </ul>
        </div>
    );
}
