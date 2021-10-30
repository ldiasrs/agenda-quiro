export const Pagination = (props) => {
    const maxItemsPerPage = 10;
    //const {paginationData} = props
   const paginationData = props.props? props.props : {totalElements:0,  totalPages:0}
    console.log(paginationData)
    const getTotalItems = () => {
        return maxItemsPerPage
    }

    // const { currentPage } = this.state;
    // const pages = this.fetchPageNumbers();

    const getPagesClick = () => {
        const pages = []
        for (let i = 0; i < paginationData.totalPages; i++) {
            pages.push(<li className="page-item"><button href="#" className="page-link">{i+1}</button></li>)
        }
        return pages
    }

    return (
        <div className="clearfix">
            <div className="hint-text">Showing <b>{getTotalItems()}</b> out of <b>{paginationData.totalElements}</b> entries</div>
            <ul className="pagination">
                <li className="page-item disabled"><button href="#">Previous</button></li>
                {
                    getPagesClick()
                }
                <li className="page-item"><button href="#" className="page-link">Next</button></li>
            </ul>
        </div>
    );
}
